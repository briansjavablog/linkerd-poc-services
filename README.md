# linkerd-poc-services

This demo project uses Linkerd service mesh to 'mesh' a number of SPring Boot microservices on Azure AKS.
The project contains the following
 * 4 simple microservices
   * BankingService - aggregagtor service that calls the 3 services listed below and returns aggregated data. This service is publicly exposed from the cluster
   * CurrentAccountService - REST API that returns current account data or specified user
   * SavingsAccountService - REST API that returns current account data or specified user
   * CreditCardService - REST API that returns credit card data or specified user
 * scripts for setting up and configuring cluster on AKS
   * ``createAKSClusterAndACR.sh`` - create resource group, cluster and container registry
   * ``installLinkerdOnCluster.sh`` - installs Linkerd and Linkerd-viz extension on the cluster
   * ``importNginxIngressControllerChart.sh`` - import nginx charts into the ACR registry created by createAKSClusterAndACR.sh
   * ``createNginxController.sh`` - create a nginx controller (required for Ingress created later)
   * ``deployAndExposeMicroservices.sh`` - uses Kubectl to create a namespace, create deployments for each microservice, create a Service for each microservice and create an Ingress for exposing the Linkerd dashboard
 * 4 Kuberntes manifests for creating
   * A namespace for microservices
   * Deployments for each microservice
   * Services for each microservice (3 internal services are ClusterIP and BankingService is a LoadBanalcer Service)  
   * An Ingress for exposing the Linkerd and grafana dashboard 
    
## Pre Reqs
You'll need the following in palce before you can run this demo.
 * An Azure account
 * A Linux environment with Docker, Kubectl and Linkerd installed
 
## Creating the Cluster and related resources
The microservices images have already been pushed to my Dockerhub repo so you dont need to worry about building the microservices images unless you want to change the code. 

 * Run ``az login`` and authenticate with Azure
 * Run ``createAKSClusterAndACR.sh`` to create a Resource Group, Cluster and Container Registry. The names of these resources are set inside the script.
 * Run ``installLinkerdOnCluster.sh`` to install Linkerd on the cluster. This make take some time (for me it took almost 6 minutes for teh Linkerd resources to stand up). Thsi script also installs the Llinkerd-viz extension which installs an on cluster metrics stack which provides metrics via Linkerd and Grfana dashboards
 * Run ``importNginxIngressControllerChart`` to install the nginx charts into your container reposiotry. The container repository was created as part of ``createAKSClusterAndACR.sh``
 * Run ``createNginxController.sh`` - creates an nginx controller (required by the Ingress we'll create later). An Ingress namespace is created and the controller is created inside that namespace.

## Verifying the cluster resources
After running the scripts above you can run a few ``Kubectl`` commands to verify that the expected resources are there.

Run ``kubectl get ns`` to see all namespaces. You should see banking-service, ingress, linkerd and linkerd-viz listed.

![image](https://user-images.githubusercontent.com/2404172/133604220-65451f6e-0177-462f-831d-4185a50c07b1.png)

Run ``kubectl get all -n linkerd`` to see the linkerd resources. You can do the same for the linkerd-viz and ingress namespaces.

![image](https://user-images.githubusercontent.com/2404172/133605640-7c7f8698-84b1-497a-900f-c6c1d61d10e8.png)

## Deploying the microservices 
 * Run ``deployAndExposeMicroservices.sh`` to install the micorservices. This script will use Kubectl to install 
   * namespace.yaml - creates a banking-services namespace for the micorservice resources we're going ot create
   * deployments.yaml - contains Deployment definitions for each of the 4 micorservices. These deployments include a Linkerd annotation that tells Linkerd to 'mesh' these resources
   * services.yaml - creates 4 Services, one for each microservice. The backend services (CurrentAccountService, SavingsAccountService & CreditCardService) each have a ClusterIP Service. The bankingService aggregator is a LoadBalancer Service with a public IP so that we can expose it outside the cluster. We'll use this later to test the microservices and see Linkerd in action.
   * ingress.yaml - creates an Ingress object (secured by Basic HTTP Auth) that exposes the Linkerd and Grafan dashboards

## Testing the microservices 
Next run ``kubectl get svc -n bank-service`` to see the Services created for the microservices. You should see the public IP of the exposed banking-service. We'll use that to test the service next.  
![image](https://user-images.githubusercontent.com/2404172/133604596-8d273c96-047d-44dd-b459-798c11fd0ad7.png)

Run ``curl 52.142.187.54/api/account-details/12345`` (replace 52.142.187.54 with the public IP from your banking-service Service). You should see a JSON payload returned.

![image](https://user-images.githubusercontent.com/2404172/133604874-7c6d358a-4ff8-47da-a19e-d076f99cd637.png)

## Testing the Linkerd and Grafan dashboards 
Run ``kubectl get all -n ingress`` to see Ingress controllers public facing IP.  

![image](https://user-images.githubusercontent.com/2404172/133605979-5f50bdaa-32e6-4149-8217-a7ef5d88abda.png)

You can use this IP to access the Linkerd and grafan dashboards. Note that this Ingress COntroller is used by the Ingress we created earlier, to expose the Linkerd and Grafana dashboards. The Ingress is secured with HTTP Basic auth so you'll be prompted for a username and password - use admin/admin. These creds are set in a Secret object that is created along with the Ingress. This approach is for demo purposes only.

After logging in , select the bank-services namespace to view the microservices metrics

![image](https://user-images.githubusercontent.com/2404172/133606846-f2cfbc6f-56d0-4d28-a206-98ff52116d54.png)

Clicking on the Grafan icons will load a pre built Grafan dashboard

![image](https://user-images.githubusercontent.com/2404172/133607043-d2aba2cd-5c60-48be-a747-4d4bfa12ab90.png)

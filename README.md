# linkerd-poc-services

This demo project uses Linkerd service mesh to 'mesh' a number of SPring Boot microservices on Azure AKS.
The project contains the following
 * 4 simple microservices
   * BankingService - aggregagtor service that calls the 3 services listed below and returns aggregated data. This service is publicly exposed from the cluster
   * CurrentAccountService - REST API that returns current account data or specified user
   * SavingsAccountService - REST API that returns current account data or specified user
   * CreditCardService - REST API that returns credit card data or specified user
 * scripts for setting up and configuring cluster on AKS
   * createAKSClusterAndACR.sh - create resource group, cluster and container registry
   * installLinkerdOnCluster.sh - installs Linkerd and Linkerd-viz extension on the cluster
   * importNginxIngressControllerChart.sh - import nginx charts into the ACR registry created by createAKSClusterAndACR.sh
   * createNginxController.sh - create a nginx controller (required for Ingress created later)
   * deployAndExposeMicroservices.sh - uses Kubectl to create a namespace, create deployments for each microservice, create a Service for each microservice and create an Ingress for exposing the Linkerd dashboard
    

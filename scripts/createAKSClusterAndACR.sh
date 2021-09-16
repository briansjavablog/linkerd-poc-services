#!/bin/sh

ACR_NAME=LinkerdPocRegistry
RESOURCE_GROUP=linkerd-poc-resource-group
CLUSTER_NAME=linkerd-poc-cluster
LOCATION=ukwest
az group create --location $LOCATION --resource-group $RESOURCE_GROUP
az acr create -n $ACR_NAME -g $RESOURCE_GROUP --sku basic
az aks create --resource-group=$RESOURCE_GROUP --name=$CLUSTER_NAME --node-count=1 --generate-ssh-keys --node-vm-size Standard_B2s --enable-managed-identity --attach-acr $ACR_NAME
az aks get-credentials --overwrite-existing --name $CLUSTER_NAME --resource-group $RESOURCE_GROUP

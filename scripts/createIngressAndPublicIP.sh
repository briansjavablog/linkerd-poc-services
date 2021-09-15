#!/bin/sh

RESOURCE_GROUP=linkerd-poc-resource-group
az network public-ip create --resource-group $RESOURCE_GROUP --name linkerd-poc-public-ip --sku Standard --allocation-method static --query publicIp.ipAddress -o tsv

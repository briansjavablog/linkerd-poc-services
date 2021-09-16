#!/bin/sh

cd ..
cd Kubernetes
kubectl apply -f namespace.yaml
kubectl apply -f deployments.yaml
kubectl apply -f services.yaml
kubectl apply -f ingress.yaml

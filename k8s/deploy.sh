#!/bin/bash
kubectl apply -f ./manifests
kubectl create configmap jenkins-config --from-file=./config/jenkins.yaml --from-file=./config/plugins.txt --namespace jenkins

cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: ServiceAccount
metadata:
  name: jenkins-agent
  namespace: jenkins
---
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: jenkins-edit
  namespace: jenkins
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: edit
subjects:
- kind: ServiceAccount
  name: jenkins-agent
  namespace: jenkins
EOF
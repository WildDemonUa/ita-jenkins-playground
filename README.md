# Jenkins Playground

## Local
```bash
sudo docker-compose up
echo http://localhost:80/login
```
Will create a playground with Jenkins Controller and a SSH Agent.
Login: admin
Password: test

To build on the SSH Agent use the following agent label:
```groovy
agent {
    label 'agent-node-label'
}
```

## K8S
You need to have a local K8s Cluster created (using Docker for Desktop, Microk8s, k3s, etc.). You need to have `kubectl` installed (not covered here).

```bash
cd ita-jenkins-playground/eks
bash deploy.sh
# Wait 5 minutes
export SERVICE_IP=$(kubectl get svc --namespace jenkins jenkins --template "{{ range (index .status.loadBalancer.ingress 0) }}{{ . }}{{ end }}")
echo http://$SERVICE_IP:80/login
```

Will create a playground with Jenkins Controller configured using Jenkins Configuration as Code with access to K8S cluster.
Login: admin
Password: verysecret

## GKE
You need to have a GKE Cluster created with atleast 3 nodes.

```bash
gcloud container clusters get-credentials CLUSTER_NAME --zone CLUSTER_ZONE --project CLUSTER_PROJECT_ID
cd gke
bash deploy.sh
# Wait 5 minutes
export SERVICE_IP=$(kubectl get svc --namespace jenkins jenkins --template "{{ range (index .status.loadBalancer.ingress 0) }}{{ . }}{{ end }}")
echo http://$SERVICE_IP:80/login
```
Will create a playground with Jenkins Controller configured using Jenkins Configuration as Code with access to K8S cluster.
Login: admin
Password: verysecret

## EKS
You need to have a EKS Cluster created with atleast 3 large nodes. You need to have EKSCTL installed (not covered here)

```bash
export ACCOUNT_ID=`aws sts get-caller-identity --query "Account" --output text`
export YOUR_NAME=your_name_here
cat << EOF > ekscfg.yaml
---
apiVersion: eksctl.io/v1alpha5
kind: ClusterConfig

metadata:
  name: ${YOUR_NAME}-eks
  region: us-east-1

iam:
  serviceRolePermissionsBoundary: "arn:aws:iam::${ACCOUNT_ID}:policy/CustomPowerUserBound"

managedNodeGroups:
- name: nodegroup
  desiredCapacity: 3
  instanceType: t2.large
  ssh:
    allow: false
  iam:
    instanceRolePermissionsBoundary: "arn:aws:iam::${ACCOUNT_ID}:policy/CustomPowerUserBound"
cloudWatch:
  clusterLogging:
    enableTypes: ["api", "audit", "controllerManager"]
availabilityZones: ['us-east-1a', 'us-east-1b', 'us-east-1c', 'us-east-1d']
EOF

eksctl create cluster -f ekscfg.yaml

cd ita-jenkins-playground/eks
bash deploy.sh
# Wait 5 minutes
export SERVICE_IP=$(kubectl get svc --namespace jenkins jenkins --template "{{ range (index .status.loadBalancer.ingress 0) }}{{ . }}{{ end }}")
echo http://$SERVICE_IP:80/login
```
Will create a playground with Jenkins Controller configured using Jenkins Configuration as Code with access to K8S cluster.
Login: admin
Password: verysecret

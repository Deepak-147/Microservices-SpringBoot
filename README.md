# Microservices using Spring Boot (with Google Cloud)

**Table of contents:**
 1. [Google Kubernetes Engine (GKE)](#gke)
 2. [How GKE works](#how-gke)
 3. [Basic terminology](#basics)
 4. [Creating Kubernetes Cluster](#create-cluster)
 5. [Connecting to a Kubernetes Cluster](#connect-cluster)
    1. [Connecting using Google Cloud Shell](#connect-gcloud-shell)
    2. [Interacting with cluster using Kubectl (Kubernetes Controller, command line interface)](#kubectl)
    3. [Interacting with the cluster using local machine](#local)
    4.  [Deployments](#deployment)
    5. [Configmaps](#configmaps)

<a id="gke"></a>

## 1. Google Kubernetes Engine (GKE)
Google Kubernetes Engine (GKE), a managed Kubernetes service that you can use to deploy and operate containerized applications at scale using Google's infrastructure.

<a id="how-gke"></a>

## 2. How GKE works
A GKE environment consists of nodes, which are Compute Engine virtual machines (VMs), that are grouped together to form a cluster. You package your apps (also called workloads) into containers. You deploy sets of containers as Pods to your nodes. You use the Kubernetes API to interact with your workloads, including administering, scaling, and monitoring.

<a id="basics"></a>

## 3. Basic terminology

- **Node** is a virtual server (server on cloud)

- **Kubernetes** manages 1000s of nodes. It has master nodes (or manager) to manage the worker nodes. 
The combination of master nodes and worker nodes forms a **cluster**

- **Containers** package an application so it can easily be deployed to run in its own isolated environment. Containers are run on Kubernetes clusters. Containers are an isolated environment to run any code.

- **Pod** is the smallest deployable unit. Pod is a collection of containers that can run on a host. A Kubernetes node can contain multiple Pods, and a Pod can contain multiple containers

- **ReplicaSet** ensures that a specified number of pod replicas are running at any given time.

- **Service** allows your application to receive traffic through a permanent address. It provides a constant frontend url for our consumers, irrespective of the changes happening in backend url due to pod changes. Load Balancer is a type of service.

![Terms](/assets/images/container.png "Terms")

<a id="create-cluster"></a>

## 4. Creating Kubernetes Cluster
![Create Cluster](/assets/images/create-cluster.png "Create Cluster")

![Create Cluster2](/assets/images/create-cluster2.png "Create Cluster2")

<a id="connect-cluster"></a>

## 5. Connecting to a Kubernetes Cluster
![Connect Cluster](/assets/images/connect-cluster.png "Connect Cluster")

```gcloud container clusters get-credentials <cluster-name> --region <region-name> --project <project-id>```

<a id="connect-gcloud-shell"></a>

### 1. Connecting using Google Cloud Shell
![Connect Cluster using Cloud shell](/assets/images/connect-using-cloud-shell.png "Connect Cluster using cloud shell")

<a id="kubectl"></a>

### 2. Interacting with cluster using Kubectl (Kubernetes Controller, command line interface)

- **1. Check version:**

```kubectl version```

![kubectl version](/assets/images/kubectl-version.png "Kubectl version")

- **2. Create Deployment:**

```kubectl create deployment <deployment name> --image=<image name>```

![Create deployment](/assets/images/create-deployment.png "Create Deployment")

- **3. Expose Deployment:**

```kubectl expose deployment <deployment name> --type=<type> --port=<port name>```

![Expose deployment](/assets/images/expose-deployment.png "Expose Deployment")

- **4. View Workloads:**

![Workloads](/assets/images/workloads.png "Workloads")

![Workloads2](/assets/images/workloads2.png "Workloads2")

- **5. Verify Endpoints:**

![verify endpoints](/assets/images/verify-endpoints.png "Verify endpoints")

- **6. get:**

```kubectl get <type>```
	
Where type can be event(s), pod(s), replicaset(s), deployment(s), service(s), all

with options:
```kubectl get <type> -o wide```

sort events by creation time:
```kubectl get events --sort-by=.metadata.creationTimestamp```

![Get command](/assets/images/kubectl-get.png "Get command")

- **7. explain:**

```kubectl explain <type>```
	
Where type can be event(s), pod(s), replicaset(s), deployment(s), service(s)

- **8. describe:**

```kubectl describe pod <pod-name>```

- **9. delete:**

```kubectl delete pods <pod name>```
	
Deletes everything (Deployments, services, pods, replicaset ...etc) about the given app-name
	
```kubectl delete all -l app=<app-name>```

![Delete](/assets/images/delete-pods.png "Delete")

Even after deleting the pod, the replicaset made sure to have sufficient number of pods running. So it sprung up another pod.

- **10. scale deployment:**
	
```kubectl scale deployment <deployment name> --replicas=<number of replicas>```

![Scale](/assets/images/scale-deployment.png "Scale")

<a id="local"></a>

### 3. Interacting with the cluster using local machine

- **1. Installing gcloud CLI**

    [Installation Guide](https://cloud.google.com/sdk/docs/install)

    ```gloud init```

    ![gcloud init](/assets/images/gcloud-init.png "gcloud init")

- **2. Installing kubectl**

    [Installation Guide](https://kubernetes.io/docs/tasks/tools/install-kubectl-macos/)

    ```kubectl version --client```

    ![kubectl version](/assets/images/kubectl-v.png "kubectl version")

    Connect to the cluster and issue the command ```kubectl version``` to check both client and server versions

    ![kubectl version](/assets/images/connect-kubectl-v.png "kubectl version")

<a id="deployment"></a>

### 4. Deployments

- **1. Creating deployment manually**

    - **1.1 Deploying currency exchange microservice**

        - ```kubectl create deployment currency-exchange --image=ldeepak/udemy-microservices-currency-exchange-service:0.0.11-SNAPSHOT```

        - ```kubectl expose deployment currency-exchange --type=LoadBalancer --port=8000```

        - ```kubectl get svc```, to get the External IP of the service.

        - Execute the request on postman: http://External IP:8000/currency-exchange/from/USD/to/INR

        ![verify endpoints](/assets/images/verify-endpoints.png "Verify endpoints")

    - **1.2 Deploying currency conversion microservice**

        - ```kubectl create deployment currency-conversion --image=ldeepak/udemy-microservices-currency-conversion-service:0.0.11-SNAPSHOT```

        - ```kubectl expose deployment currency-conversion --type=LoadBalancer --port=8100```

        - ```kubectl get svc```, to get the External IP of the service.

        - Execute the request on postman: http://External IP:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

        ![currency conversion](/assets/images/currency-conversion.png "Currency conversion")

- **2. Creating deployment using declarative YAML configuration file**

    - Create your deployment file
    - ```kubectl apply -f <deployment-file-name.yaml>```
    - ```kubectl get all```, to check the deployment
    - ```kubectl get svc```, to get the External IP of the service
    - Execute the request on postman: http://External IP:8000/currency-exchange/from/USD/to/INR

- **3. Check difference in deployment file**

    ```kubectl diff -f <deployment-file-name.yaml>```

- **4. Check deployment history**

    ```kubectl rollout history deployment <app_name>```

<a id="configmaps"></a>

### 5. Configmaps

- **1. Create configmap**

    ```kubectl create configmap <configmap_name> --from-literal=<key>=<value>```

    ![configmap](/assets/images/configmap.png "Config map")

- **2. Get config maps**

    ```kubectl get configmap```

- **3. Get yaml configuration of the configmap into a file**

    ```kubectl get configmap <configmap_name> -o yaml >> <file_name.yaml>```
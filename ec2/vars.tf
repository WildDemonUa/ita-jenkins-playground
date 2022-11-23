variable "name" {
  description = "The name used to namespace resources created by this module"
}

variable "ita_group" {
  description = "ITA Group name"
}

variable "key_pair_name" {
  description = "The name of a Key Pair that you've created in AWS and have saved on your computer. You will be able to use this Key Pair to SSH to the EC2 instance."
}

variable "region" {
  default = "us-east-1"
}

variable "plugins" {
  type = list(string)
  default = [
    "configuration-as-code:latest",
    "credentials:latest",
    "ssh-credentials:latest",
    "git:latest",
    "maven-plugin:latest",
    "github:latest",
    "ssh-slaves:latest",
    "workflow-aggregator:latest",
    "job-dsl:latest",
    "dashboard-view:latest",
    "matrix-auth:latest",
    "theme-manager:latest",
    "material-theme:latest"
  ]
}

variable "name" {
  description = "The name used to namespace resources created by this module"
}

variable "ita_group" {
  description = "ITA Group name"
}

variable "key_pair_name" {
  description = "The name of a Key Pair that you've created in AWS and have saved on your computer. You will be able to use this Key Pair to SSH to the EC2 instance."
}

variable "vpc_id" {

}

variable "subnet_id" {

}

variable "plugins" {
  type = list(string)
}

# Jenkins on EC2 Terraform

Please provide variable for:
* `name`: The name used to namespace resources created by this module.
* `ita_group`: ITA Group name.
* `key_pair_name`: The name of a Key Pair that you've created in AWS and have saved on your computer. You will be able to use this Key Pair to SSH to the EC2 instance.

> Note: Starting Jenkins on t2.micro takes ~5 minutes
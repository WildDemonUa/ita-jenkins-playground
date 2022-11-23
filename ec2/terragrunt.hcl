terraform {
  extra_arguments "core_vars" {
    commands = get_terraform_commands_that_need_vars()

    arguments = [
      "-var-file=./aws.tfvars"
    ]
  }
}
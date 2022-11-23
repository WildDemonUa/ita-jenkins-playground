output "url" {
  value = "http://${module.jenkins.ip}:8080"
}

output "ssh" {
  value = "ssh ec2-user@${module.jenkins.ip}"
}

data "aws_ami" "amzn_linux" {
  most_recent = true

  owners = ["amazon"]

  filter {
    name   = "root-device-type"
    values = ["ebs"]
  }

  filter {
    name   = "name"
    values = ["amzn2-ami-hvm-*-x86_64-ebs"]
  }
}

resource "aws_instance" "jenkins" {
  ami                         = data.aws_ami.amzn_linux.image_id
  instance_type               = "t2.micro"
  vpc_security_group_ids      = [aws_security_group.default.id]
  subnet_id                   = var.subnet_id
  user_data                   = data.template_file.user_data.rendered
  key_name                    = var.key_pair_name
  associate_public_ip_address = true

  lifecycle {
    ignore_changes = [ami]
  }

  tags = {
    Name      = var.name
    ita_group = var.ita_group
  }
  volume_tags = {
    ita_group = var.ita_group
  }
}

data "template_file" "user_data" {
  template = file("${path.module}/user-data.sh")
  vars = {
    plugins = join("\n", var.plugins)
  }
}

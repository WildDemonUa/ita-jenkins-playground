resource "aws_vpc" "default" {
  cidr_block = "192.168.0.0/16"

  tags = {
    Name      = "${var.name} VPC"
    ita_group = var.ita_group
  }
}

resource "aws_internet_gateway" "default_igw" {
  vpc_id = aws_vpc.default.id

  tags = {
    Name      = "${var.name} VPC"
    ita_group = var.ita_group
  }
}

resource "aws_subnet" "public" {
  vpc_id     = aws_vpc.default.id
  cidr_block = "192.168.0.0/24"
  tags = {
    Name      = "${var.name} Public subnet"
    ita_group = var.ita_group
  }
}

resource "aws_route_table" "public" {
  vpc_id = aws_vpc.default.id

  tags = {
    Name      = "${var.name} Public Routes"
    ita_group = var.ita_group
  }
}

resource "aws_route" "public_route" {
  route_table_id         = aws_route_table.public.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.default_igw.id
}

resource "aws_route_table_association" "public" {
  route_table_id = aws_route_table.public.id
  subnet_id      = aws_subnet.public.id
}

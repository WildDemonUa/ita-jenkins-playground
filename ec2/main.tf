module "network" {
  source    = "./network"
  ita_group = var.ita_group
  name      = var.name
}

module "jenkins" {
  source        = "./jenkins"
  ita_group     = var.ita_group
  name          = var.name
  key_pair_name = var.key_pair_name
  vpc_id        = module.network.vpc_id
  subnet_id     = module.network.public_subnet_id
  plugins       = var.plugins
}

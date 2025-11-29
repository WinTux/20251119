package docker.no_root

# No ejecutar como root
deny contains msg if {
  some i
  input[i].instruction == "USER"
  lower(input[i].value) == "root"
  msg := "No se permite ejecutar el contenedor como root (USER root)"
}

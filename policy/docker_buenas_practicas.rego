package main
import rego.v1
# No utilizar tag latest
deny contains msg if {
  some i
  lower(input[0][i].Cmd) == "from"
  endswith(lower(input[0][i].Value), ":latest")
  msg := "No usar tag 'latest' en la sentencia FROM"
}

# El Dockerfile debería exponer al menos un puerto
deny contains msg if {
  not any_expose
  msg := "Dockerfile no expone nungún puerto ¡debe exponer puertos!"
}
any_expose if {
  some i
  lower(input[0][i].Cmd) == "expose"
}

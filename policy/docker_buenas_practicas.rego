package docker.buenaspracticas

# No utilizar tag latest
deny contains msg if {
  some i
  input[i].instruction == "FROM"
  endswith(lower(input[i].value), ":latest")
  msg := "No usar tag 'latest' en la sentencia FROM"
}

# El Dockerfile debería exponer al menos un puerto
deny contains msg if {
  not any_expose
  msg := "Dockerfile no expone nungún puerto ¡debe exponer puertos!"
}
any_expose if {
  some i
  input[i].instruction == "EXPOSE"
}

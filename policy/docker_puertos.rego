package docker.puertos

deny contains msg if {
  some i
  input[i].instruction == "EXPOSE"
  not puerto_valido(input[i].value)
  msg := sprintf("Puerto %v no es válido, solo se permiten 8080, 8081 y 8082", [input[i].value])
}

puerto_valido(p) if {
  p == "8081"
}
puerto_valido("8082")
puerto_valido("8080")

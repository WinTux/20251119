package main
import rego.v1
deny contains msg if {
  some i
  lower(input[0][i].Cmd) == "expose"
  not puerto_valido(input[0][i].Value)
  msg := sprintf("Puerto %v no es válido, solo se permiten 8080, 8081 y 8082", [input[0][i].Value])
}

puerto_valido(p) if {
  p == "8081"
}
puerto_valido("8082")
puerto_valido("8080")

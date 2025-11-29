package main
import rego.v1
deny contains msg if {
  some i
  lower(input[0][i].Cmd) == "from"
  contains(lower(input[0][i].Value), "8-jdk")
  msg := "No se permite usar JDK 8"
}
deny contains msg if { 
  some i
  lower(input[0][i].Cmd) == "from"
  contains(lower(input[0][i].Value), "17-jdk")
  msg := "No se permite usar JDK 17"
}
deny contains msg if { 
  some i
  lower(input[0][i].Cmd) == "from"
  contains(lower(input[0][i].Value), "25-jdk")
  msg := "No se permite usar JDK 25"
}
deny contains msg if { 
  some i
  lower(input[0][i].Cmd) == "from"
  not contains(lower(input[0][i].Value), "21-jdk")
  msg := sprintf("La imagen base debe ser JDK 21, la versión detectada es %v", [input[i].value])
}


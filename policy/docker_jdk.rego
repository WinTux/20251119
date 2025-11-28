package docker.jdk

deny contains msg if {
  some i
  input[i].instruction == "FROM"
  contains(lower(input[i].value), "8-jdk")
  msg := "No se permite usar JDK 8"
}
deny contains msg if { 
  some i
  input[i].instruction == "FROM"
  contains(lower(input[i].value), "17-jdk")
  msg := "No se permite usar JDK 17"
}
deny contains msg if { 
  some i
  input[i].instruction == "FROM"
  contains(lower(input[i].value), "25-jdk")
  msg := "No se permite usar JDK 25"
}
deny contains msg if { 
  some i
  input[i].instruction == "FROM"
  not contains(lower(input[i].value), "21-jdk")
  msg := sprintf("La imagen base debe ser JDK 21, la versión detectada es %v", [input[i].value])
}


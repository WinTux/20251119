package docker.multistage

deny contains msg if {
  not es_multi_etapa
  msg := "Dockerfile sin pulti-etapas (se requiere más de una etapa, no solo build)"
}

es_multi_etapa if {
  count([x | x := input[_]; x.instruction == "FROM"]) > 1
}

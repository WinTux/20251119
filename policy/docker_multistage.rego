package main
import rego.v1
deny contains msg if {
  not es_multi_etapa
  msg := "Dockerfile sin pulti-etapas (se requiere más de una etapa, no solo build)"
}

es_multi_etapa if {
  count([1 |
    some i
    lower(input[0][i].Cmd) == "from"
  ]) > 1
}

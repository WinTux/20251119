# Manejo básico de Prometheus
## Métricas HTTP (requests)
### Para mostrar las peticiones ocurridas
http_server_requests_seconds_count
### Para mostrar peticiones por end-point
sum by(uri) (http_server_requests_seconds_count)
### Para mostrar el total de errores
sum by(status) (http_server_requests_seconds_count)
### Para mostrar el total de errores 5XX
sum by(status) (http_server_requests_seconds_count{status=~"5.."})

## Métricas JVM
### Memoria usada (heap)
jvm_memory_used_bytes{area="heap"}
### Memoria no heap
jvm_memory_used_bytes{area="nonheap"}
### Consumo de GC
rate(jvm_gc_pause_seconds_count[5m])

## Métricas de CPU
### Uso de la CPU por proceso
process_cpu_usage
### Media de carga
system_cpu_usage

### Acerca de queries en: https://prometheus.io/docs/prometheus/latest/querying/

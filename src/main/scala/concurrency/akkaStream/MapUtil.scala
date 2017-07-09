package concurrency.akkaStream

object MapUtil {
  def mergeMaps[K,V](map1: Map[K, V], map2: Map[K, V])(f:(V,V) => V): Map[K, V] = {
    map2.foldLeft(map1) { case (acc, (k, v)) =>
      if (acc.contains(k)) {
        acc.updated(k, f(acc(k), v))
      } else {
        acc.updated(k, v)
      }
    }
  }
}

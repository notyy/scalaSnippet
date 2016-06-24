#@namespace scala in.xnnyygn.dictservice

exception DictServiceException {
  1: string description
}

service DictService {

  string get(1: string key) throws(1: DictServiceException ex)

  void put(1: string key, 2: string value)
}
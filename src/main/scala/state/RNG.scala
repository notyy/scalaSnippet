package state

import state.RNG.Rand

trait RNG {
  val int: Rand[Int] = _.nextInt

  def nextInt: (Int, RNG)
}

case class State[S,+A](run: S => (A,S)) {

  def unit(a: A): S => (A,S) = s => (a,s)

  def map[B](f: A => B): S => (B,S) = s => {
    val (a, s1) = run(s)
    (f(a),s1)
  }
}

object RNG {
  type State[S,+A] = S => (A, S)
  type Rand[A] = State[RNG, A]

  def simple(seed: Long): RNG = new RNG {
    def nextInt = {
      val seed2 = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      ((seed2 >>> 16).asInstanceOf[Int],
        simple(seed2))
    }
  }

  def positiveInt: Rand[Int] = rng => {
//    map(rng.int){ i =>
//      if (i != Int.MinValue) i.abs else positiveInt(rng)
//    }
//    rng.nextInt match {
//      case (v, r) => if (v != Int.MinValue) (v.abs, r) else positiveInt(r)
//    }
    ???
  }

  def double: Rand[Double] = { rng =>
    val (v, r) = positiveInt(rng)
    (v / Int.MaxValue, r)
  }

  def intDouble: Rand[(Int, Double)] = { rng =>
    val (i, r1) = positiveInt(rng)
    val (d, r2) = double(r1)
    ((i, d), r2)
  }

  def doubleInt: Rand[(Double, Int)] = { rng =>
    intDouble(rng) match {
      case ((i, d), r) => ((d, i), r)
    }
  }

  def double3: Rand[(Double, Double, Double)] = { rng =>
    double(rng) match {
      case (d1, r1) => double(r1) match {
        case (d2, r2) => double(r2) match {
          case (d3, r3) => ((d1, d2, d3), r3)
        }
      }
    }
  }

  def ints(count: Int): Rand[List[Int]] = rng => (count, rng) match {
    case (0, _) => (Nil, rng)
    case (i, r) => positiveInt(rng) match {
      case (x, r1) => ints(i - 1)(r1) match {
        case (l, r2) => (x :: l, r2)
      }
    }
  }

  def unit[S,A](a: A): S => (A,S) = rng => (a, rng)


  def positiveMax(n: Int): Rand[Int] = map(positiveInt)(_ / Int.MaxValue * n)

  def doubleByMap: Rand[Double] = map(positiveInt)(_ / Int.MaxValue)

  def intDoubleByMap2:Rand[(Int,Double)] = map2(positiveInt, doubleByMap)((_,_))

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = {
    fs.foldLeft(unit[RNG,List[A]](List[A]()))((acc, r) => map2(acc, r)((l, a) => a :: l))
  }

  def map[S,A, B](s:S => (A,S))(f: A => B): S => (B,S) = r => {
    val (v, r1) = s(r)
    (f(v), r1)
  }

  def flatMap[S,A,B](f: S => (A,S))(g: A => (S => (B,S))): S => (B,S) =  rng => {
    val (a,r1) = f(rng)
    g(a)(r1)
  }

  def mapByFlatMap[S,A, B](a: S => (A,S))(f: A => B): S => (B,S) = flatMap(a)(f andThen unit)

  def map2[A,B,C](r1: Rand[A], r2: Rand[B])(f: (A,B) => C) : Rand[C] = r => {
    val (a, rg1) = r1(r)
    val (b, rg2) = r2(rg1)
    (f(a,b), rg2)
  }

//  def map2ByFlatMap[A,B,C](r1: Rand[A], r2: Rand[B])(f: (A,B) => C) : Rand[C] = r => {
//    val (a, rg1) =  r1(r)
//    flatMap(r1)(x => r2(rg1))
//  }
}

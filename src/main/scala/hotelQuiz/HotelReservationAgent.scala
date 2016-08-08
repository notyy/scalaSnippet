package hotelQuiz

object HotelReservationAgent {
  def recommendHotel: String => Seq[Hotel] => String = { input => hotels =>
    //selectHotel(calcPrice(hotels)(parse(input))).name
    (parse andThen calcPrice(hotels) andThen selectHotel andThen (_.name)) (input)
  }

  def showName: Hotel => String = _.name

  //"Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)"
  def parse: String => Agenda = { input =>
    val arr = input.split(":")
    val customerType = CustomerType.fromString(arr.head.trim)
    val dates = arr.last.split(",").map(_.split("\\(|\\)")).map(ds => (ds.head.trim, ds.last.trim))
    Agenda(customerType, dates)
  }

  trait CustomerType

  object CustomerType {
    def fromString(str: String): CustomerType = str match {
      case "Regular" => Regular
      case "Rewards" => Rewards
      case _ => throw new IllegalArgumentException("unknown customer type")
    }
  }

  case object Regular extends CustomerType

  case object Rewards extends CustomerType

  type Date = String
  type Day = String

  case class Agenda(customerType: CustomerType, dates: Seq[(Date, Day)])

  type Price = Double

  def calcPrice: Seq[Hotel] => Agenda => Seq[(Hotel, Price)] = { hotels => agenda =>
    hotels.map(hotel => calcPriceSingle(hotel)(agenda))
  }

  def calcPriceSingle: Hotel => Agenda => (Hotel, Price) = { hotel => agenda =>
    val customerType = agenda.customerType
    val price = agenda.dates.map { case (_, day) => dayToDayType(day) }.map {
      case WeekDay if customerType == Regular => hotel.regularWeekDayPrice
      case WeekDay if customerType == Rewards => hotel.rewardsWeekDayPrice
      case WeekEnd if customerType == Regular => hotel.regularWeekEndPrice
      case WeekEnd if customerType == Rewards => hotel.rewardsWeekEndPrice
    }.sum
    (hotel, price)
  }

  trait DayType

  case object WeekDay extends DayType

  case object WeekEnd extends DayType

  def dayToDayType: Day => DayType = {
    case ("mon" | "tues" | "wed" | "thur" | "fri") => WeekDay
    case ("sat" | "sun") => WeekEnd
    case d => throw new IllegalArgumentException(s"unknow day type:$d")
  }

  type Rate = Int

  case class Hotel(name: String, rate: Rate,
                   regularWeekDayPrice: Price, rewardsWeekDayPrice: Price,
                   regularWeekEndPrice: Price, rewardsWeekEndPrice: Price
                  )

  def selectHotel: Seq[(Hotel, Price)] => Hotel = _.sortWith {
    case ((hotel1, price1), (hotel2, price2)) => {
      if (price1 == price2) hotel1.rate > hotel2.rate else price1 < price2
    }
  }.head._1
}

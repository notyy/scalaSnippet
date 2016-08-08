package hotelPractice.fp

import akka.io.Inet.SO.ReuseAddress

object HotelReservationService {
  /*
  Problem Four: Hotel Reservation Problem
A hotel chain operating in Miami wishes to offer room reservation services over the internet.
They have three hotels in Miami: Lakewood, Bridgewood and Ridgewood. Each hotel has
separate weekday and weekend(Saturday and Sunday) rates. There are special rates for
rewards customer as a part of loyalty program. Each hotel has a rating assigned to it.

● Lakewood with a rating of 3 has weekday rates as 110$ for regular customer and 80$ for
rewards customer. The weekend rates are 90$ for regular customer and 80$ for a rewards
customer.
● Bridgewood with a rating of 4 has weekday rates as 160$ for regular customer and 110$ for
rewards customer. The weekend rates are 60$ for regular customer and 50$ for a rewards
customer.
● Ridgewood with a rating of 5 has weekday rates as 220$ for regular customer and 100$ for
rewards customer. The weekend rates are 150$ for regular customer and 40$ for a rewards
customer.
Write a program to help an online customer find the cheapest hotel.
The input to the program will be a range of dates for a regular or rewards customer. The output should
be the cheapest available hotel. In case of a tie, the hotel with highest rating should be returned.

INPUT FORMAT:
<customer_type>: <date1>, <date2>, <date3>, …
OUTPUT FORMAT:
<name_of_the_cheapest_hotel>
INPUT 1:
Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)
OUTPUT 1:
Lakewood
INPUT 2:
Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun)
OUTPUT 2:
Bridgewood
INPUT 3:
Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)
OUTPUT 3:
Ridgewood
   */
  def recommendHotel: Seq[Hotel] => String => String = { hotels => input =>
    (parse andThen calcPrice(hotels) andThen selectHotel andThen (_.name))(input)
  }

  //Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun)
  def parse: String => Agenda = { input =>
    val arr = input.split(":")
    val customerType = CustomerType.fromString(arr.head.trim)
    val dates = arr.last.split(",").map(_.split("\\(|\\)")).map(ds => (ds.head.trim, ds.last.trim))
    Agenda(customerType, dates)
  }

  type Date = String
  type Day = String

  case class Agenda(customerType: CustomerType, dates: Seq[(Date, Day)])

  trait CustomerType
  object CustomerType{
    def fromString(str:String): CustomerType = str match {
      case "Regular" => Regular
      case "Rewards" => Rewards
      case x => throw new IllegalArgumentException(s"unknown customer type: $x")
    }
  }

  case object Regular extends CustomerType

  case object Rewards extends CustomerType

  type Price = Double

  def calcPrice: Seq[Hotel] => Agenda => Seq[(Hotel, Price)] = { hotels => agenda =>
    hotels.map(hotel => calcPriceSingle(hotel)(agenda))
  }

  def calcPriceSingle: Hotel => Agenda => (Hotel, Price) = { hotel => agenda =>
    val customerType = agenda.customerType
    val price = agenda.dates.map{ case (_,day) => dayToDayType(day)}.map {
      case Weekday if customerType == Regular => hotel.regularWeekdayPrice
      case Weekday if customerType == Rewards => hotel.rewardsWeekdayPrice
      case Weekend if customerType == Regular => hotel.regularWeekendPrice
      case Weekend if customerType == Rewards => hotel.rewardsWeekendPrice
    }.sum
    (hotel, price)
  }

  trait DayType
  case object Weekday extends DayType
  case object Weekend extends DayType

  def dayToDayType: Day => DayType = {
    case ("mon"|"tues"|"wed"|"thur"|"fri") => Weekday
    case ("sat"|"sun") => Weekend
    case x => throw new IllegalArgumentException(s"unknown daytype $x")
  }

  case class Hotel(name: String, rating: Int,
                   regularWeekdayPrice: Price, rewardsWeekdayPrice: Price,
                   regularWeekendPrice: Price, rewardsWeekendPrice: Price
                  )

  def selectHotel: Seq[(Hotel, Price)] => Hotel = _.sortWith{
    case ((hotel1,price1),(hotel2,price2)) => {
        if(price1 == price2) hotel1.rating > hotel2.rating else price1 < price2
    }
  }.head._1
}

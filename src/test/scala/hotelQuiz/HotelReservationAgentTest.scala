package hotelQuiz

import hotelQuiz.HotelReservationAgent._
import org.scalatest.{FunSpec, ShouldMatchers}

class HotelReservationAgentTest extends FunSpec with ShouldMatchers {
  private val lakewood: Hotel = Hotel(
    name = "LakeWood", rate = 3,
    regularWeekDayPrice = 110, rewardsWeekDayPrice = 80,
    regularWeekEndPrice = 90, rewardsWeekEndPrice = 80
  )
  private val bridgewood: Hotel = Hotel(
    name = "Bridgewood", rate = 4,
    regularWeekDayPrice = 160, rewardsWeekDayPrice = 110,
    regularWeekEndPrice = 60, rewardsWeekEndPrice = 50
  )
  private val ridgewood: Hotel = Hotel(
    name = "Ridgewood", rate = 5,
    regularWeekDayPrice = 220, rewardsWeekDayPrice = 100,
    regularWeekEndPrice = 150, rewardsWeekEndPrice = 40
  )
  val hotels: Seq[Hotel] = List(lakewood, bridgewood, ridgewood)

  describe("HotelReservationAgent") {

    it("can select hotel for regular customer for weekday") {
      HotelReservationAgent.recommendHotel("Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)")(hotels) shouldBe "LakeWood"
    }

    it("can select hotel for regular customer containing weekday and weekend") {
      HotelReservationAgent.recommendHotel("Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun)")(hotels) shouldBe "Bridgewood"
    }

    it("can select hotel for rewards customer containing weekday and weekend") {
      HotelReservationAgent.recommendHotel("Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)")(hotels) shouldBe "Ridgewood"
    }

    it("can parse input to Agenda") {
      val input = "Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)"
      val agenda = HotelReservationAgent.parse(input)
      agenda.customerType shouldBe Regular
      agenda.dates should have size 3
      agenda.dates.head shouldBe("16Mar2009", "mon")
    }

    it("can calculate price for a single hotel for a agenda") {
      val agenda = Agenda(Regular, List(("20Mar2009", "fri")))
      HotelReservationAgent.calcPriceSingle(hotels.head)(agenda)._2 shouldBe 110
    }

    it("can convert from day to day type") {
      HotelReservationAgent.dayToDayType("mon") shouldBe WeekDay
      HotelReservationAgent.dayToDayType("sat") shouldBe WeekEnd
    }

    it("should select cheapest hotel") {
      HotelReservationAgent.selectHotel(List((lakewood, 100.0), (bridgewood, 200.0))) shouldBe lakewood
    }

    it("should select high rate hotel if price is same") {
      HotelReservationAgent.selectHotel(List((lakewood, 100.0), (bridgewood, 100.0))) shouldBe bridgewood
    }
  }
}

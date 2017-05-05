package hotelPractice.fp

import hotelPractice.fp.HotelReservationService.{Agenda, Hotel, Regular}
import org.scalatest.{FunSpec, Matchers}

class HotelReservationServiceTest extends FunSpec with Matchers {
  private val lakewood: Hotel = Hotel(
    name = "LakeWood", rating = 3,
    regularWeekdayPrice = 110.0,
    rewardsWeekdayPrice = 80,
    regularWeekendPrice = 90,
    rewardsWeekendPrice = 80
  )
  private val bridgewood: Hotel = Hotel(
    name = "Bridgewood", rating = 4,
    regularWeekdayPrice = 160.0,
    rewardsWeekdayPrice = 110,
    regularWeekendPrice = 60,
    rewardsWeekendPrice = 50
  )
  private val ridgewood: Hotel = Hotel(
    name = "Ridgewood", rating = 5,
    regularWeekdayPrice = 220.0,
    rewardsWeekdayPrice = 100,
    regularWeekendPrice = 150,
    rewardsWeekendPrice = 40
  )

  val hotels: Seq[Hotel] = List(
    lakewood,
    bridgewood,
    ridgewood
  )

  describe("HotelReservationService") {

    it("can select hotel for regular customer in weekday") {
      HotelReservationService.recommendHotel(hotels)("Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)") shouldBe "LakeWood"
    }

    it("can select hotel for regular customer containing weekday and weekend") {
      HotelReservationService.recommendHotel(hotels)("Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun)") shouldBe "Bridgewood"
    }

    it("can select hotel for rewards customer containing weekday and weekend") {
      HotelReservationService.recommendHotel(hotels)("Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)") shouldBe "Ridgewood"
    }

    it("can parse input to Agenda") {
      val input = "Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)"
      val agenda = HotelReservationService.parse(input)
      agenda.customerType shouldBe Regular
      agenda.dates should have size 3
      agenda.dates.head._1 shouldBe "16Mar2009"
      agenda.dates.head._2 shouldBe "mon"
    }

    it("can calculate price for a agenda in a single hotel") {
      val agenda = Agenda(Regular, List(("16Mar2009", "mon")))
      HotelReservationService.calcPriceSingle(lakewood)(agenda) shouldBe (lakewood,110)
    }

    it("should select cheapest hotel"){
      HotelReservationService.selectHotel(List((lakewood, 100.0), (bridgewood,200.0))) shouldBe lakewood
    }

    it("should select higher rate if price is same"){
      HotelReservationService.selectHotel(List((lakewood, 100.0), (bridgewood,100.0))) shouldBe bridgewood
    }
  }
}

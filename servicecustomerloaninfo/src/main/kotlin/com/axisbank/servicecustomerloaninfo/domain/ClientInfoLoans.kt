package com.axisbank.servicecustomerloaninfo.domain

class ClientInfoLoans {
    private var customer: Customer? = null
    private var businessLoan: BusinessLoan? = null
    private var educationalLoan: EducationalLoan? = null
    private var housingLoan: HousingLoan? = null
    private var vehicalLoan: VehicleLoan? = null
    constructor(customer: Customer?,businessLoan: BusinessLoan?,educationalLoan: EducationalLoan?,housingLoan: HousingLoan?,vehicalLoan: VehicleLoan?){
        this.customer=customer
        this.businessLoan=businessLoan
        this.educationalLoan=educationalLoan
        this.housingLoan=housingLoan
        this.vehicalLoan=vehicalLoan
    }
}
package com.axisbank.loanofferservice.domain


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Document
class HousingLoan {
    @Id
    private var housingLoanId: String? = null
    @NotNull
    @Positive(message = "customer mobile number should be a positive value")
    private var customerMobileNo: Long? = null
    @NotBlank(message = "housing loan needs address ")
    private var address: String? = null
    @NotBlank(message = "loan name cannot be blank value")
    private var loanName: String? = null
    @NotNull
    @Positive(message = "loan amount should be a positive value")
    private var loanamount: Int? = null
    @Min(value = 0L, message = "rating of interest should not be negative")
    private var rateOfInterest: Double? = null
    private var status: String? = null
    fun getHousingLoanId():String?{
        return this.housingLoanId;
    }
    fun getAddress():String?{
        return this.address
    }
    fun getCustomerMobileNo():Long?{
        return this.customerMobileNo;
    }
    fun getLoanName():String?{
        return this.loanName;
    }
    fun getLoanamount():Int?{
        return this.loanamount;
    }
    fun setLoanamount(loanamount: Int?){
        this.loanamount=loanamount
    }
    fun getRateOfInterest():Double?{
        return this.rateOfInterest;
    }
    fun getStatus():String?{
        return this.status;
    }

    fun setHousingLoanId(housingLoanId:String?){
        this.housingLoanId=housingLoanId;
    }
    fun setAddress(address:String?){
        this.address=address;
    }
    fun setCustomerMobileNo(customerMobileNo:Long?){
        this.customerMobileNo=customerMobileNo
    }
    fun setLoanName(loanName:String?){
        this.loanName=loanName;
    }
    fun setRateOfInterest(rateOfInterest:Double?){
        this.rateOfInterest=rateOfInterest;
    }
    fun setStatus(status:String?){
        this.status=status;
    }
    constructor(){}

    constructor(housingLoanId: String?,
                customerMobileNo: Long?,
                address: String?,
                loanName: String?,
                loanamount: Int?,
                rateOfInterest: Double?,
                status: String?){
        this.housingLoanId=housingLoanId
        this.customerMobileNo=customerMobileNo
        this.address=address
        this.loanName=loanName
        this.loanamount=loanamount
        this.rateOfInterest=rateOfInterest
        this.status=status
    }


}
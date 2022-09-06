package com.axisbank.loanofferservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Document
class EducationalLoan {
    @Id
    private var educationalLoanId: String? = null
    @NotBlank(message = "educational instituation name is required in order to proceed")
    private var collegeName: String? = null
    @NotNull
    @Positive(message = "customer mobile number should be a positive value")
    private var customerMobileNo: Long? = null
    @NotBlank(message = "loan name cannot be blank value")
    private var loanName: String? = null
    @NotNull
    @Positive(message = "loan amount should be a positive value")
    private var loanamount: Int? = null
    @Min(value = 0L, message = "rating of interest should not be negative")
    private var rateOfInterest: Double? = null
    private var status: String? = null

    fun getEducationalLoanId():String?{
        return this.educationalLoanId;
    }
    fun getCollegeName():String?{
        return this.collegeName;
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
    fun getRateOfInterest():Double?{
        return this.rateOfInterest;
    }
    fun getStatus():String?{
        return this.status;
    }

    fun setEducationalLoanId(educationalLoanId:String?){
        this.educationalLoanId=educationalLoanId;
    }
    fun setCollegeName(collegeName:String?){
        this.collegeName=collegeName
    }
    fun setCustomerMobileNo(customerMobileNo:Long?){
        this.customerMobileNo=customerMobileNo
    }
    fun setLoanName(loanName:String?){
        this.loanName=loanName;
    }
    fun setLoanamount(loanamount: Int?){
        this.loanamount=loanamount
    }
    fun setRateOfInterest(rateOfInterest:Double?){
        this.rateOfInterest=rateOfInterest;
    }
    fun setStatus(status:String?){
        this.status=status;
    }
    constructor(){}
    constructor(educationalLoanId: String?, collegeName: String, customerMobileNo: Long?, loanName: String?, loanamount: Int?, rateOfInterest: Double?, status: String?){
        this.educationalLoanId=educationalLoanId
        this.collegeName=collegeName
        this.customerMobileNo=customerMobileNo
        this.loanName=loanName
        this.loanamount=loanamount
        this.rateOfInterest=rateOfInterest
        this.status=status
    }

}
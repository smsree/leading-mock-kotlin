package com.axisbank.loanapp.domain

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document



@Document
class Customer {


    @Id
    private var customerId: String? = null
    private var name: String? = null
    private var work: String? = null
    private var adharNo: Long? = null
    private var panNo: String? = null
    private var mobileNo: Long? = null
    private var dob: String? = null
    fun getCustomerId():String?{
        return this.customerId;
    }
    fun setCustomerId(customerId:String?){
        this.customerId=customerId;
    }
    fun getName():String?{
        return this.name;
    }
    fun setName(name:String?){
        this.name=name;
    }
    fun getWork():String?{
        return this.work;
    }
    fun setWork(work:String?){
        this.work=work;
    }
    fun getAdharNo():Long?{
        return this.adharNo
    }
    fun setAdharNo(adharNo:Long?){
        this.adharNo=adharNo
    }
    fun getPanNo():String?{
        return this.panNo;
    }
    fun setPanNo(panNo:String?){
        this.panNo=panNo;
    }
    fun getMobileNo():Long?{
        return this.mobileNo;
    }
    fun setMobileNo(mobileNo:Long?){
        this.mobileNo=mobileNo;
    }
    fun getDob():String?{
        return this.dob;
    }
    fun setDob(dob:String?){
        this.dob=dob;
    }
    constructor(customerId: String?, name: String?, work: String?, adharNo: Long?, panNo: String?, mobileNo: Long?, dob: String?){
        this.customerId=customerId;
        this.name=name;
        this.work=work;
        this.adharNo=adharNo
        this.panNo=panNo;
        this.mobileNo=mobileNo;
        this.dob=dob;
    }
    constructor(){

    }


}

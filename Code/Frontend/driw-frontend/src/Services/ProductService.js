import axios from 'axios'
import APIService from './APIService'
export default class ProductService {


    constructor(){
        
        this.apiService = new APIService();

    }

    /**
     * service method for get all products
     */
    getProducts(){
        return new Promise((resolve,reject) => {
            this.apiService.get("product/all").then(response =>{
                resolve(response);
            }).catch(err =>{
                console.log(err)
            })
        });
    }

    /**
     * method for get product by ts uuid
     * @param {*} uuid 
     */
    getProductByUuid(uuid) {
        return new Promise((resolve, reject) => {
            this.apiService.get("product/" + uuid).then(response => {
                resolve(response);
            }).catch(err => {
                console.log(err)
            })
        });
    }

    /**
     * this is the method to calculate the price according to the its qty
     * @param {*} productDTO 
     */
    getTotalPrice(productDTO){
        return new Promise((resolve, reject) => {
            this.apiService.post("product/purchase/",productDTO).then(response => {
                resolve(response);
            }).catch(err => {
                console.log(err)
            })
        });
    }
}

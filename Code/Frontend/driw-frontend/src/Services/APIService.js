import axios from 'axios'
export default class APIService {


    constructor(){
        this.baseURL = "http://localhost:2850/";

    }

    get(url){
        return new Promise((resolve,reject) => {
            axios.get(this.baseURL + url).then(response =>{
                if(response.status === 200){
                    resolve(response);
                }
            }).catch(err => {
                resolve(err);
            });
        });
    }

    post(url, data) {
        return new Promise((resolve, reject) => {
            axios.post(this.baseURL + url, data).then(response => {
                resolve(response);
            }).catch(err => {
                resolve(err);
            });
        });
    }
}

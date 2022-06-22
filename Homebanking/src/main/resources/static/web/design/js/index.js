const app = Vue.createApp({
   
    data(){
        return {
     
      firstName: "",
      lastName: "",
      regusername:"",
      regpassword:"",
      mail: "",
      password: "",
      formButton: true,


        }
    },

    created() {
      
    },

    methods: {

        toggleFormButton() {
            if (this.formButton == true){
                this.formButton = false
            } else {
                this.formButton = true
            }
        },


        postLogin(){
            axios.post('/api/login',`email=${this.mail}&password=${this.password}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log('signed in!!!')

            window.location.href = '/web/accounts.html'
        })
            
            
           
            
        },
        postRegistration(){
            axios.post('/api/clients',`name=${this.firstName}&apellido=${this.lastName}&email=${this.regusername}&password=${this.regpassword}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log('registered')

                axios.post('/api/login',`email=${this.regusername}&password=${this.regpassword}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response=> {
                axios.post('/api/clients/current/accounts')
            })    
                
            .then(response=> 
                window.location.href = '/web/accounts.html'
                )
              
                })
           
            

        }

    },
    

    computed: {

    }
}) .mount("#app")
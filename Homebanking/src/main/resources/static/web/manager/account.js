


Vue.createApp({

    data(){
        return {
       
       
       accounts:[],
       transactions:[],
        }
    },

    created() {

        const urlParams = new URLSearchParams(window.location.search);
        const myParam  = urlParams.get('id');
        axios.get(`/api/accounts/${myParam}`)
        .then(datos => {
            this.transactions  = datos.data.transactions;
        
            
           
          
            }),
        
      
        axios.get('/api/clients/current')
        .then(datos =>{
            this.accounts = datos.data.accounts
                
        })
        


       },

   
       methods: {
        
           
        postLogout(){
            axios.post('/api/logout').then(response => {
                console.log('signed out!!!')
                window.location.href = "/web/index.html"
            }
                )
        }
           
   
       },
   
       computed: {
   
       }
   }) .mount("#app")
Vue.createApp({

    data(){
        return {
      clients: [],
      Json: [],
      nombres: "",
      apellidos: "",
      mail: "",
        }
    },

    created() {
     axios.get('http://localhost:8080/rest/clients')
     .then(datos => {
         this.clients = datos.data._embedded.clients
         this.Json = datos.data
        
     })
    },

    methods: {
        postClient(){
            axios.post('http://localhost:8080/rest/clients', {
               name :  this.nombres,
               apellido : this.apellidos,
               email : this.mail
            } )
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })
        }

    },
    

    computed: {

    }
}) .mount("#app")
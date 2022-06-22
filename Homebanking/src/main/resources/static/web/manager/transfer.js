Vue.createApp({

    data() {
        return {

            accounts: [],
            amount: "",
            description: "",
            originAccountNumber: "",
            destinyAccountNumber: "",
            accountType: "myAccounts",
            myAccounts: "",
            ThirdAccounts: "",


        }
    },



    created() {
        axios.get('/api/clients/current/accounts')
            .then(datos => {
                this.accounts = datos.data
                this.accounts.sort((a, b) => a.id - b.id)
            })
    },

    methods: {
        postLogout() {
            axios.post('/api/logout').then(response => {
                console.log('signed out!!!')
                window.location.href = "http://localhost:8080/web/index.html"
            })
        },

        postTransaction() {
            axios.post('/api/transactions',
                    `amount=${this.amount}&description=${this.description}&originAccountNumber=${this.originAccountNumber}&destinyAccountNumber=${this.destinyAccountNumber}`)
                
                .catch(error => {
                    console.log(error.message)
                })
                    .then(response => {
                    console.log("transaction complete")
                })
              
                
        }

    },

    computed: {

    }
}).mount("#app")
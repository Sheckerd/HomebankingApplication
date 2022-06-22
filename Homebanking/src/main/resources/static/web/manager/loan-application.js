Vue.createApp({

    data() {
        return {

            accounts: [],
            loans: {},
        
            idLoan: 1,
            amount: "",
            payments: "",
            destinationAccount: "",

            
        }
    },



    created() {
        axios.get('/api/clients/current/accounts')
            .then(datos => {
                this.accounts = datos.data
                this.accounts.sort((a, b) => a.id - b.id)

            }),
            axios.get('/api/loans')
            .then(datos => {
                this.loans = datos.data
                this.loans.sort((a, b) => a.id - b.id)
            })

           
    },




    methods: {
        postLogout() {
            axios.post('/api/logout').then(response => {
                console.log('signed out!!!')
                window.location.href = "/web/index.html"
            })
        },

        postLoan() {
            console.log({idLoan: this.idLoan, amount: this.amount, payments: this.payments, destinationAccount: this.destinationAccount })
            axios.post('/api/loans', {idLoan: this.idLoan, amount: this.amount, payments: this.payments, destinationAccount: this.destinationAccount })
                .then(response => {
                    console.log("Loan complete")
                })
                .then(response => {
                    location.reload()
                })

        },

        selectPayments() {
            let aux = [...this.loans]
            aux = aux.filter((loan) => this.idLoan == loan.id);
            return aux[0].payments.sort((a,b) => a - b)
            
        }

    },

    computed: {


    }
}).mount("#app")
Vue.createApp({

    data() {
        return {
            current: [],
            accounts: [],
            loans: [],

            nombre: "",
            apellido: "",
            cuenta: "",

        }
    },



    created() {

        axios.get('/api/clients/current')
            .then(datos => {

                this.current = datos.data
                this.accounts = datos.data.accounts
                this.accounts.sort((a, b) => a.id - b.id)

                this.loans = datos.data.clientloans
            })


    },





    methods: {
        postLogout() {
            axios.post('/api/logout').then(response => {
                console.log('signed out!!!')
                window.location.href = "/web/index.html"
            })
        },
        postCreateAccount() {
            axios.post('/api/clients/current/accounts')
                .then(response => {
                    location.reload();
                })
        }

    },

    computed: {

    }
}).mount("#app")
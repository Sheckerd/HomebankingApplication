let id = ""

Vue.createApp({

    data() {
        return {

            cards: [],
            cardType: "CREDIT",
            cardColor: "TITANIUM",
            cardActive: false,


        }
    },

    created() {


        axios.get('/api/clients/current')
            .then(datos => {
                this.cards = datos.data.cards
            })

    },


    methods: {


            

        postLogout() {
            axios.post('/api/logout').then(response => {
                console.log('signed out!!!')
                window.location.href = "/web/index.html"
            })
        },

        postCard() {
            axios.post('/api/clients/current/cards', `cardType=${this.cardType}&cardColor=${this.cardColor}`)
                .then(response => {
                    location.reload();

                })
        },

        deleteCard() {
            axios.post('api/cards/1', `cardActive=${this.cardActive}` )
            .then(response => {
            location.reload();
            })

  
    }},

    computed: {

    }
}).mount("#app")
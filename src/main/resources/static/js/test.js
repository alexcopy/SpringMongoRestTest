function getIndex(list, id) {
    for (var i in list) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var msgRes = Vue.resource('lets/messages{/id}');
Vue.component('message-row', {
    props: ['message', 'editMessage', 'messages'],
    template: "<div>" +
        "<i>({{ message.id }})</i>{{ message.msgtext }}" +
        "<span style='position: absolute; right: 0;'>" +
        "<input type='button' value='Edit' @click='edit' />" +
        "<input type='button' value='x' @click='del' />" +
        "</span>" +
        "</div>",
    methods: {
        edit: function () {
            this.editMessage(this.message)
        },
        del: function () {
            msgRes.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                    this.messages.splice(this.messages.indexOf(this.message), 1);
                }
            });
        }
    }
});
Vue.component('messages-list', {
    props: ['messages'],
    data: function () {
        return {message: null}
    },
    template: "<div style='position: relative; width: 350px;'>" +
        "<message-form :messages='messages' :messageAttr='message'/>" +
        "<message-row v-for='message in messages' " +
        ":key='message.id' :messages='messages' " +
        ":message='message' :editMessage='editMessage'/>" +
        "</div>",
    created: function () {
        msgRes.get().then(result =>
            result.json().then(
                data => data.forEach(message => this.messages.push(message)))
        );
    },
    methods: {
        editMessage: function (message) {
            this.message = message;
        }
    }
});


Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function () {
        return {
            msgtext: '',
            id: ''
        };
    },
    watch: {
        messageAttr: function (newVal, oldVal) {
            this.msgtext = newVal.msgtext;
            this.id = newVal.id;
        }
    },
    template: "<div>" +
        "<input type='text' placeholder='write Msg'  v-model='msgtext' />" +
        "<input type='button' value='save' @click='save' />" +
        '<br />' +
        '<br />' +
        "</div>",
    methods: {
        save: function () {
            var message = {msgtext: this.msgtext};
            if (this.id) {
                msgRes.update({id: this.id}, message).then(result => result.json().then(data => {
                        var index = getIndex(this.messages, data.id);
                        this.messages.splice(index, 1, data);
                        this.msgtext = '';
                        this.id = '';
                    })
                );
            } else {
                msgRes.save({}, message).then(result => result.json().then(data => {
                    this.messages.push(data);
                    this.msgtext = '';
                }));
            }
        }
    }
});

var app = new Vue({
    el: '#app',
    template: "<messages-list :messages='messages'/>",
    data: {
        messages: []
    }
});
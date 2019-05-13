function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var messageApi = Vue.resource('/mongo{/keySeq}');

Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function () {
        return {
            name: '',
            keySeq: '',
            course: ''
        }
    },
    watch: {
        messageAttr: function (newVal, oldVal) {
            this.name = newVal.name;
            this.course = newVal.course;
            this.keySeq = newVal.keySeq;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Name" v-model="name" />' +
        '<input type="text" placeholder="Course" v-model="course" />' +
        '<input type="button" value="Save" @click="save" />' +
        '<br />' +
        '<br />' +
        '</div>',
    methods: {
        save: function () {
            var message = {name: this.name, course:this.course};

            if (this.keySeq) {
                messageApi.update({keySeq: this.keySeq}, message).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.messages, data.keySeq);
                        this.messages.splice(index, 1, data);
                        this.course = ''
                        this.name = ''
                        this.keySeq = ''
                    })
                )
            } else {
                messageApi.save({}, message).then(result =>
                    result.json().then(data => {
                        this.messages.push(data);
                        this.course = ''
                        this.name = '',
                        this.keySeq = ''
                    })
                )
            }
        }
    }
});

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template: '<div>' +
        '<i>({{ message.keySeq }})</i> {{ message.name }}' +
        ' ( {{ message.course   }})  ' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        },
        del: function () {
            messageApi.remove({keySeq: this.message.keySeq}).then(result => {
                if (result.ok) {
                    this.messages.splice(this.messages.indexOf(this.message), 1)
                }
            })
        }
    }
});

Vue.component('messages-list', {
    props: ['messages'],
    data: function () {
        return {
            message: null
        }
    },
    template:
        '<div style="position: relative; width: 350px;">' +
        '<message-form :messages="messages" :messageAttr="message" />' +
        '<message-row v-for="message in messages" :key="message.keySeq" :message="message" ' +
        ':editMethod="editMethod" :messages="messages" />' +
        '</div>',
    created: function () {
        messageApi.get().then(result =>
            result.json().then(data =>
                data.forEach(message => this.messages.push(message))
            )
        )
    },
    methods: {
        editMethod: function (message) {
            this.message = message;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
});
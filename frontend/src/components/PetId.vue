<template>
    <v-card outlined @click="openDialog">
        <PetPicker v-model="value" @selected="pick" :editMode="editMode" />

        <v-dialog v-model="pickerDialog">
            <v-card>
                <v-card-title>Pet</v-card-title>
                <v-card-text>
                    <PetPicker v-model="value" @selected="pick" :editMode="editMode" />
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="deep-purple lighten-2" text @click="pickerDialog = false">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-card>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'PetId',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            inList: Boolean,
            label: String,
        },
        data: () => ({
            newValue: {},
            pickerDialog: false,
        }),
        async created() {
            if(!Object.values(this.value)[0]) {
                this.$emit('input', {});
                this.newValue = {
                    'id': '',
                }
            }
            else {
                this.newValue = this.value;
            }
        },
        watch: {
            value(val) {
                this.$emit('input', val);
            },
            newValue(val) {
                this.$emit('input', val);
            },
        },

        methods: {
            edit() {
                this.editMode = true;
            },
            async add() {
                this.editMode = false;
                this.$emit('input', this.value);

                if(this.isNew){
                    this.$emit('add', this.value);
                } else {
                    this.$emit('edit', this.value);
                }
            },
            async remove(){
                this.editMode = false;
                this.isDeleted = true;

                this.$emit('input', this.value);
                this.$emit('delete', this.value);
            },
            change(){
                this.$emit('change', this.value);
            },
            openDialog() {
                var path = '/pets/';

                if(this.editMode) {
                    this.pickerDialog = true;
                } else {
                    this.pickerDialog = false;
                    this.$router.push(path + this.value.id);
                }
            },
            async pick(val){
                this.newValue = val;
            },
        }
    }
</script>



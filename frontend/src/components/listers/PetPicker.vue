<template>
    <div>
        <v-card-title v-if="!editMode">
            Pet :  {{ referenceValue.name }} 
        </v-card-title>
        <v-list two-line v-else-if="editMode && list.length > 0">
            <v-list-item-group 
                    v-model="selected" 
                    color="indigo"
                    @change="select"
            >
                <v-list-item v-for="(item, idx) in list" :key="idx">
                    <template v-slot:default="{ active }">
                        <v-list-item-avatar color="grey darken-1">
                        </v-list-item-avatar>
                        
                        <v-list-item-content>
                            <v-list-item-title>
                                {{item.name }}
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                Name :  {{item.name }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Type :  {{item.type }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Age :  {{item.age }}
                            </v-list-item-subtitle>
                        </v-list-item-content>

                        <v-list-item-action>
                            <v-checkbox :input-value="active" color="indigo"></v-checkbox>
                        </v-list-item-action>
                    </template>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </div>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'PetPicker',
        props: {
            value: [String, Object, Array, Number, Boolean],
            editMode: Boolean,
        },
        data: () => ({
            list: [],
            selected: null,
            referenceValue: null,
        }),
        async created() {
            var me = this;
            var temp = await axios.get(axios.fixUrl('/pets'))
            if(temp.data) {
                me.list = temp.data._embedded.pets;
            }

            if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
                var id = Object.values(me.value)[0];
                var tmpValue = await axios.get(axios.fixUrl('/pets/' + id))
                if(tmpValue.data) {
                    var val = tmpValue.data
                    
                    me.selected = me.list.findIndex((item) => item.name == val.name)
                    
                    
                    me.referenceValue = val
                }
            }
        },
        methods: {
            select(val) {
                var obj = {}
                if(val != undefined) {
                    var arr = this.list[val]._links.self.href.split('/');
                    obj['id'] = arr[4]; 
                    
                    
                    
                    this.$emit('selected', obj);
                }
            },
        },
    };
</script>



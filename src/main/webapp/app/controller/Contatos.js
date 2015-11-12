Ext.define('ExtMVC.controller.Contatos', {
    extend: 'Ext.app.Controller',
    stores: ['Contatos'],
    models: ['Contato'],
    views: ['contato.Formulario', 'contato.Grid'],
    refs: [{
            ref: 'contatoPanel',
            selector: 'panel'
        }, {
            ref: 'contatoGrid',
            selector: 'grid'
        }
    ],
    init: function () {
        this.control({
            'contatogrid dataview': {
                itemdblclick: this.editarContato
            },
            'contatogrid button[action=add]': {
                click: this.editarContato
            },
            'contatogrid button[action=delete]': {
                click: this.deleteContato
            },
            'contatoform button[action=save]': {
                click: this.updateContato
            }
        });
    },
    editarContato: function (grid, record) {
        var edit = Ext.create('ExtMVC.view.contato.Formulario').show();

        if (record) {
            edit.down('form').loadRecord(record);
        }
    },
    updateContato: function (button) {
        var win = button.up('window'),
                form = win.down('form'),
                record = form.getRecord(),
                values = form.getValues();

        var novo = false;

        if (values.id > 0) {
            record.set(values);
        } else {
            record = Ext.create('ExtMVC.model.Contato');
            record.set(values);
            this.getContatosStore().add(record);
            novo = true;
        }

        win.close();
        this.getContatosStore().sync();
        this.getContatosStore().load();

        if (novo) { //faz reload para atualziar
            this.getContatosStore().load();
        }
    },
    deleteContato: function (button) {
        var grid = this.getContatoGrid(),
                record = grid.getSelectionModel().getSelection(),
                store = this.getContatosStore();

        if (record.length === 0) {
            //Ext.Msg.alert('Erro', 'Nenhuma linha selecionada');
            Ext.Msg.show({
                title: 'Erro',
                msg: 'Nenhuma linha selecionada!',
                icon: Ext.MessageBox.ERROR,
                scope: this,
                width: 100
            });
        } else {
            Ext.Msg.show({
                title: 'Confirmação',
                msg: 'Tem certeza que deseja apagar o(s) registro(s) selecionado(s)?',
                buttons: Ext.Msg.YESNO,
                icon: Ext.MessageBox.WARNING,
                scope: this,
                width: 450,
                fn: function (btn, ev) {
                    if (btn == 'yes') {
                        store.remove(record);
                        store.sync();
                        this.getContatosStore().load();
                    }
                }
            });
        }
    }
    
     });

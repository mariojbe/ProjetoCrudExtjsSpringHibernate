Ext.define('ExtMVC.view.contato.Formulario', {
    extend: 'Ext.window.Window',
    alias: 'widget.contatoform',
    requires: ['Ext.form.Panel', 'Ext.form.field.Text'],
    title: 'Editar/Criar Contato',
    layout: 'fit',
    autoShow: true,
    width: 280,
    iconCls: 'icon-user',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                padding: '5 5 0 5',
                border: false,
                style: 'background-color: #fff;',
                fieldDefaults: {
                    anchor: '100%',
                    labelAlign: 'left',
                    allowBlank: false,
                    combineErrors: true,
                    msgTarget: 'side'
                },
                items: [
                    {
                        xtype: 'textfield',
                        name: 'id',
                        fieldLabel: 'id',
                        hidden: true
                    },
                    {
                        xtype: 'textfield',
                        name: 'name',
                        id: 'name',
                        fieldLabel: 'Nome',
                        listeners: {
                            afterrender: function (fld) {
                                fld.focus(false, 500);
                            }
                        }
                    },
                    {
                        xtype: 'textfield',
                        name: 'phone',
                        fieldLabel: 'Telefone'
                    },
                    {
                        xtype: 'textfield',
                        name: 'email',
                        fieldLabel: 'Email',
                        vtype: 'email'
                    }
                ]
            }
        ];

        this.dockedItems = [{
                xtype: 'toolbar',
                dock: 'bottom',
                id: 'buttons',
                ui: 'footer',
                items: ['->', {
                        iconCls: 'icon-save',
                        text: 'Salvar',
                        formBind: true,
                        action: 'save'
                    }, {
                        iconCls: 'icon-reset',
                        text: 'Limpar',
                        scope: this,
                        handler: function (btn) {
                            btn.up('window').down('form').getForm().reset();
                            Ext.getCmp('name').focus(true,10);
                        } 
                        //handler: this.close
                    }]
            }];

        this.callParent(arguments);
    }
});

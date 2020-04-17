﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:2.0.50727.112
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace CarOrderServiceClient.localhost
{
    using System.Runtime.Serialization;
    using System;
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "3.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://schemas.datacontract.org/2004/07/CarOrderServiceLib")]
    [System.SerializableAttribute()]
    public partial class CarOrderDetails : object, System.Runtime.Serialization.IExtensibleDataObject
    {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string ColorField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string MakeField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private double PriceField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private CarOrderServiceClient.localhost.CarOrderDetails SecondChoiceField;
        
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData
        {
            get
            {
                return this.extensionDataField;
            }
            set
            {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string Color
        {
            get
            {
                return this.ColorField;
            }
            set
            {
                this.ColorField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string Make
        {
            get
            {
                return this.MakeField;
            }
            set
            {
                this.MakeField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public double Price
        {
            get
            {
                return this.PriceField;
            }
            set
            {
                this.PriceField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public CarOrderServiceClient.localhost.CarOrderDetails SecondChoice
        {
            get
            {
                return this.SecondChoiceField;
            }
            set
            {
                this.SecondChoiceField = value;
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://IntertechTraining.com", ConfigurationName="CarOrderServiceClient.localhost.ICarOrder")]
    public interface ICarOrder
    {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://IntertechTraining.com/ICarOrder/PlaceOrder", ReplyAction="http://IntertechTraining.com/ICarOrder/PlaceOrderResponse")]
        int PlaceOrder(string make, string color, double price);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://IntertechTraining.com/ICarOrder/PlaceOrderWithDetails", ReplyAction="http://IntertechTraining.com/ICarOrder/PlaceOrderWithDetailsResponse")]
        int PlaceOrderWithDetails(CarOrderServiceClient.localhost.CarOrderDetails order);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://IntertechTraining.com/ICarOrder/CheckOrderStatus", ReplyAction="http://IntertechTraining.com/ICarOrder/CheckOrderStatusResponse")]
        string CheckOrderStatus(int orderID);
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    public interface ICarOrderChannel : CarOrderServiceClient.localhost.ICarOrder, System.ServiceModel.IClientChannel
    {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    public partial class CarOrderClient : System.ServiceModel.ClientBase<CarOrderServiceClient.localhost.ICarOrder>, CarOrderServiceClient.localhost.ICarOrder
    {
        
        public CarOrderClient()
        {
        }
        
        public CarOrderClient(string endpointConfigurationName) : 
                base(endpointConfigurationName)
        {
        }
        
        public CarOrderClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress)
        {
        }
        
        public CarOrderClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress)
        {
        }
        
        public CarOrderClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress)
        {
        }
        
        public int PlaceOrder(string make, string color, double price)
        {
            return base.Channel.PlaceOrder(make, color, price);
        }
        
        public int PlaceOrderWithDetails(CarOrderServiceClient.localhost.CarOrderDetails order)
        {
            return base.Channel.PlaceOrderWithDetails(order);
        }
        
        public string CheckOrderStatus(int orderID)
        {
            return base.Channel.CheckOrderStatus(orderID);
        }
    }
}

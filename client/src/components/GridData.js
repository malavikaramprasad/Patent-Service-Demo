import React, {Component} from "react";
import 'react-redux-datatable/dist/styles.css';
import ReactTable from 'react-table';
import "react-table/react-table.css";
import { VictoryPie } from 'victory';
import axios from 'axios';
import './GridData.css';

const queryUrl = "http://localhost:8080/patentservice/api/v1/patent";

class GridData extends Component{
    constructor() {
        super();
        this.state = {data:undefined, filter:"", textValue:"", alertText:"", barData:undefined};
    }

    componentDidMount(){
        axios.get(queryUrl+"/all")
            .then(res => {
                console.log(res.data);
                console.log("hi1");
                this.setState({data: res.data});
                this.getBarData();
            })
            .catch(err=>{
                console.log(err);
            })
    }

    filterBasedOnPieSelection(value){
        axios.get(queryUrl+"?document_type="+value)
            .then(res => {
                console.log(res.data);
                this.setState({data: res.data});
            })
            .catch(err=>{
                console.log(err);
            });
    }

    fetchData(url){
        axios.get(url)
            .then(res => {
                console.log(res.data);
                this.setState({data: res.data});
                this.getBarData();
            })
            .catch(err=>{
                console.log(err);
            });
    }

    handleGoClick(){
        if(this.state.filter && this.state.textValue){
            this.setState({alertText:""});
            let url = queryUrl + "?" + this.state.filter + '=' + this.state.textValue;
            this.fetchData(url);
        }
        else{
            this.setState({alertText:"Enter search criteria and text"});
            //window.alert("Specify all data");
        }
    }

    handleReset(){
        this.setState({filter: "", textValue:"", alertText:""});
        this.fetchData(queryUrl + "/all");
    }

    getBarData(){
        const {data} = this.state;
        const barData = [];
        if(data !== undefined){
            let r = data.reduce((map, dOld) => {
                if(map[dOld.documentType]){
                    map[dOld.documentType] += 1;
                } else {
                    map[dOld.documentType] = 1;
                }
                return map;
            }, {});

            Object.entries(r).map(i => {
                let obj = Object.create(null);
                obj["key"] = i[0];
                obj["value"] = i[1];
                barData.push(obj);
            });
        }
        this.setState({barData:barData});
    }

    render() {
        // const {data} = this.state;
        return (
            <div>
                {this.state.alertText === "" ? null : <div className="alert">
                    {this.state.alertText}
                </div>
}
                <div className="boxBorder">
                    <h3 className="surveyTitle">Search: </h3>
                    <select className="dropdown" value={this.state.filter} onChange={(event) => { this.setState({filter: event.target.value})}} >
                        <option className="options" value=""> Fetch Patents by...  </option>
                        <option className="options" value="document_id"> Document ID </option>
                        <option className="options" value="application_number"> Application Number </option>
                        <option className="options" value="patent_number"> Patent Number </option>
                        <option className="options" value="searchText"> Search Text </option>
                        <option className="options" value="title"> Title </option>
                        <option className="options" value="assignee"> Assignee </option>
                        <option className="options" value="inventor"> Inventor </option>
                    </select>
                    {/*<div className="dispInline"> */}
                        <input value={this.state.textValue} placeholder="Search Text" className="input" type="text" onChange={(event) => { this.setState({textValue: event.target.value})}}/>
                    {/*</div>*/}
                    <button className="button" onClick={this.handleGoClick.bind(this)}> Go </button>
                    <button className="button" onClick={this.handleReset.bind(this)}> Reset </button>
                </div>
                <div style={{height: '250px'}}>
                    {/*// <VictoryChart>*/}
                    <VictoryPie colorScale={["#008080", "#66b2b2"]} data={this.state.barData} x="key" y="value"
                                events={[
                                    {
                                        target: "data",
                                        eventHandlers: {
                                            onClick: (event, props) => {
                                                console.log(props.datum.key);
                                                this.filterBasedOnPieSelection(props.datum.key);
                                            }
                                        }
                                    }
                                ]}
                    />
                    {/*</VictoryChart>*/}
                </div>
                <ReactTable
                    data={this.state.data}
                    filterable
                    columns={[
                        {
                            // Header: "Data",
                            columns: [
                                {
                                    Header: "Applicant",
                                    accessor: "applicant"
                                },
                                {
                                    Header: "Title",
                                    accessor: "title",
                                },
                                {
                                    Header: "Document ID",
                                    accessor: "documentId",
                                    width: 120
                                },
                                {
                                    Header: "Application Number",
                                    accessor: "applicationNumber",
                                },
                                {
                                    Header: "Document Type",
                                    accessor: "documentType",
                                    width: 70
                                },
                                {
                                    Header: "Inventor",
                                    accessor: "inventor",
                                    width: 200
                                },
                                //Its the same
                                // {
                                //     Header: "Archive URL",
                                //     accessor: "archiveUrl"
                                // },
                                {
                                    Header: "Patent Number",
                                    accessor: "patentNumber",
                                },
                                //It's the same
                                {
                                    Header: "Publication Date",
                                    accessor: "publicationDate",
                                },
                                {
                                    Header: "Document Date",
                                    accessor: "documentDate",
                                },
                                {
                                    Header: "Production Date",
                                    accessor: "productionDate",
                                },
                                {
                                    Header: "Application Date",
                                    accessor: "applicationDate",
                                },
                               // Is the same
                                {
                                    // filterable: false,
                                    Header: "archiveUrl",
                                    accessor: "archiveUrl",
                                },
                               // Always 2018
                                {
                                    Header: "year",
                                    accessor: "year",
                                },
                                {
                                    Header: "Assignee",
                                    accessor: "assignee",
                                }
                            ]
                        }
                    ]}
                    defaultPageSize={5}
                    className="-striped -highlight"
                />
                <br />

            </div>
        );
    }
}

export default GridData;

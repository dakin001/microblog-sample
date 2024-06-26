/*
 * MoiKiitos API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 *
 * Swagger Codegen version: 3.0.52
 *
 * Do not edit the class manually.
 *
 */
import {ApiClient} from "../ApiClient";
import {FeedQueryDto} from '../model/FeedQueryDto';
import {Post} from '../model/Post';
import {PostCreateDto} from '../model/PostCreateDto';
import {ResponseBodyEmitter} from '../model/ResponseBodyEmitter';

/**
* Post service.
* @module api/PostApi
* @version v1
*/
export class PostApi {

    /**
    * Constructs a new PostApi. 
    * @alias module:api/PostApi
    * @class
    * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
    * default to {@link module:ApiClient#instanc
    e} if unspecified.
    */
    constructor(apiClient) {
        this.apiClient = apiClient || ApiClient.instance;
    }

    /**
     * Callback function to receive the result of the createPost operation.
     * @callback moduleapi/PostApi~createPostCallback
     * @param {String} error Error message, if any.
     * @param data This operation does not return a value.
     * @param {String} response The complete HTTP response.
     */

    /**
     * submit a new post
     * submit a new post
     * @param {module:model/PostCreateDto} body 
     * @param {module:api/PostApi~createPostCallback} callback The callback function, accepting three arguments: error, data, response
     */
    createPost(body, callback) {
      
      let postBody = body;
      // verify the required parameter 'body' is set
      if (body === undefined || body === null) {
        throw new Error("Missing the required parameter 'body' when calling createPost");
      }

      let pathParams = {
        
      };
      let queryParams = {
        
      };
      let headerParams = {
        
      };
      let formParams = {
        
      };

      let authNames = [];
      let contentTypes = ['application/json'];
      let accepts = [];
      let returnType = null;

      return this.apiClient.callApi(
        '/posts', 'POST',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, callback
      );
    }
    /**
     * Callback function to receive the result of the myFeed operation.
     * @callback moduleapi/PostApi~myFeedCallback
     * @param {String} error Error message, if any.
     * @param {module:model/ResponseBodyEmitter{ data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * login user&#x27;s real time feed stream
     * Real time Gets the feed of posts from login user
     * @param {module:model/FeedQueryDto} reqDto 
     * @param {module:api/PostApi~myFeedCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link <&vendorExtensions.x-jsdoc-type>}
     */
    myFeed(reqDto, callback) {
      
      let postBody = null;
      // verify the required parameter 'reqDto' is set
      if (reqDto === undefined || reqDto === null) {
        throw new Error("Missing the required parameter 'reqDto' when calling myFeed");
      }

      let pathParams = {
        
      };
      
      let headerParams = {
        
      };
      let formParams = {
        
      };

      let authNames = [];
      let contentTypes = [];
      let accepts = ['text/event-stream'];
      let returnType = ResponseBodyEmitter;

      return this.apiClient.callApi(
        '/feed', 'GET',
        pathParams, reqDto, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, callback
      );
    }
    /**
     * Callback function to receive the result of the queryUserFeed operation.
     * @callback moduleapi/PostApi~queryUserFeedCallback
     * @param {String} error Error message, if any.
     * @param {Array.<module:model/Post>{ data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * specific user&#x27;s feed
     * Gets the feed of posts from a specific user
     * @param {Number} userId 
     * @param {module:model/FeedQueryDto} reqDto 
     * @param {module:api/PostApi~queryUserFeedCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link <&vendorExtensions.x-jsdoc-type>}
     */
    queryUserFeed(userId, reqDto, callback) {
      
      let postBody = null;
      // verify the required parameter 'userId' is set
      if (userId === undefined || userId === null) {
        throw new Error("Missing the required parameter 'userId' when calling queryUserFeed");
      }
      // verify the required parameter 'reqDto' is set
      if (reqDto === undefined || reqDto === null) {
        throw new Error("Missing the required parameter 'reqDto' when calling queryUserFeed");
      }

      let pathParams = {
        'userId': userId
      };
   
      let headerParams = {
        
      };
      let formParams = {
        
      };

      let authNames = [];
      let contentTypes = [];
      let accepts = ['*/*'];
      let returnType = [Post];

      return this.apiClient.callApi(
        '/feed/{userId}', 'GET',
        pathParams, reqDto, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, callback
      );
    }

}
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
import {PageResultUser} from '../model/PageResultUser';
import {User} from '../model/User';
import {UserFollowQueryDto} from '../model/UserFollowQueryDto';
import {UserQueryDto} from '../model/UserQueryDto';

/**
* User service.
* @module api/UserApi
* @version v1
*/
export class UserApi {

    /**
    * Constructs a new UserApi. 
    * @alias module:api/UserApi
    * @class
    * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
    * default to {@link module:ApiClient#instanc
    e} if unspecified.
    */
    constructor(apiClient) {
        this.apiClient = apiClient || ApiClient.instance;
    }

    /**
     * Callback function to receive the result of the findUser operation.
     * @callback moduleapi/UserApi~findUserCallback
     * @param {String} error Error message, if any.
     * @param {module:model/User{ data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * find user
     * @param {module:model/UserQueryDto} queryDto 
     * @param {module:api/UserApi~findUserCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link <&vendorExtensions.x-jsdoc-type>}
     */
    findUser(queryDto, callback) {
      
      let postBody = null;
      // verify the required parameter 'queryDto' is set
      if (queryDto === undefined || queryDto === null) {
        throw new Error("Missing the required parameter 'queryDto' when calling findUser");
      }

      let pathParams = {
        
      };
      let queryParams = {
        'queryDto': queryDto
      };
      let headerParams = {
        
      };
      let formParams = {
        
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['*/*'];
      let returnType = User;

      return this.apiClient.callApi(
        '/users', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, callback
      );
    }
    /**
     * Callback function to receive the result of the follow operation.
     * @callback moduleapi/UserApi~followCallback
     * @param {String} error Error message, if any.
     * @param data This operation does not return a value.
     * @param {String} response The complete HTTP response.
     */

    /**
     * follow user
     * follow user api
     * @param {Number} userId 
     * @param {module:api/UserApi~followCallback} callback The callback function, accepting three arguments: error, data, response
     */
    follow(userId, callback) {
      
      let postBody = null;
      // verify the required parameter 'userId' is set
      if (userId === undefined || userId === null) {
        throw new Error("Missing the required parameter 'userId' when calling follow");
      }

      let pathParams = {
        'userId': userId
      };
      let queryParams = {
        
      };
      let headerParams = {
        
      };
      let formParams = {
        
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = [];
      let returnType = null;

      return this.apiClient.callApi(
        '/users/{userId}/follow', 'POST',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, callback
      );
    }
    /**
     * Callback function to receive the result of the listFollowers operation.
     * @callback moduleapi/UserApi~listFollowersCallback
     * @param {String} error Error message, if any.
     * @param {module:model/PageResultUser{ data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * list Followers
     * @param {Number} userId 
     * @param {module:model/UserFollowQueryDto} queryDto 
     * @param {module:api/UserApi~listFollowersCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link <&vendorExtensions.x-jsdoc-type>}
     */
    listFollowers(userId, queryDto, callback) {
      
      let postBody = null;
      // verify the required parameter 'userId' is set
      if (userId === undefined || userId === null) {
        throw new Error("Missing the required parameter 'userId' when calling listFollowers");
      }
      // verify the required parameter 'queryDto' is set
      if (queryDto === undefined || queryDto === null) {
        throw new Error("Missing the required parameter 'queryDto' when calling listFollowers");
      }

      let pathParams = {
        'userId': userId
      };
      let queryParams = {
        'queryDto': queryDto
      };
      let headerParams = {
        
      };
      let formParams = {
        
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['*/*'];
      let returnType = PageResultUser;

      return this.apiClient.callApi(
        '/users/{userId}/followers', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, callback
      );
    }
    /**
     * Callback function to receive the result of the listFollowing operation.
     * @callback moduleapi/UserApi~listFollowingCallback
     * @param {String} error Error message, if any.
     * @param {module:model/PageResultUser{ data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * list Following
     * @param {Number} userId 
     * @param {module:model/UserFollowQueryDto} queryDto 
     * @param {module:api/UserApi~listFollowingCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link <&vendorExtensions.x-jsdoc-type>}
     */
    listFollowing(userId, queryDto, callback) {
      
      let postBody = null;
      // verify the required parameter 'userId' is set
      if (userId === undefined || userId === null) {
        throw new Error("Missing the required parameter 'userId' when calling listFollowing");
      }
      // verify the required parameter 'queryDto' is set
      if (queryDto === undefined || queryDto === null) {
        throw new Error("Missing the required parameter 'queryDto' when calling listFollowing");
      }

      let pathParams = {
        'userId': userId
      };
      let queryParams = {
        'queryDto': queryDto
      };
      let headerParams = {
        
      };
      let formParams = {
        
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['*/*'];
      let returnType = PageResultUser;

      return this.apiClient.callApi(
        '/users/{userId}/following', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, callback
      );
    }
    /**
     * Callback function to receive the result of the unfollow operation.
     * @callback moduleapi/UserApi~unfollowCallback
     * @param {String} error Error message, if any.
     * @param data This operation does not return a value.
     * @param {String} response The complete HTTP response.
     */

    /**
     * unfollow user
     * unfollow user api
     * @param {Number} userId 
     * @param {module:api/UserApi~unfollowCallback} callback The callback function, accepting three arguments: error, data, response
     */
    unfollow(userId, callback) {
      
      let postBody = null;
      // verify the required parameter 'userId' is set
      if (userId === undefined || userId === null) {
        throw new Error("Missing the required parameter 'userId' when calling unfollow");
      }

      let pathParams = {
        'userId': userId
      };
      let queryParams = {
        
      };
      let headerParams = {
        
      };
      let formParams = {
        
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = [];
      let returnType = null;

      return this.apiClient.callApi(
        '/users/{userId}/unfollow', 'POST',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, callback
      );
    }

}
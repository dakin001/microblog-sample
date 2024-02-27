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
import {ApiClient} from '../ApiClient';

/**
 * The UserQueryDto model module.
 * @module model/UserQueryDto
 * @version v1
 */
export class UserQueryDto {
  /**
   * Constructs a new <code>UserQueryDto</code>.
   * @alias module:model/UserQueryDto
   * @class
   */
  constructor() {
  }

  /**
   * Constructs a <code>UserQueryDto</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/UserQueryDto} obj Optional instance to populate.
   * @return {module:model/UserQueryDto} The populated <code>UserQueryDto</code> instance.
   */
  static constructFromObject(data, obj) {
    if (data) {
      obj = obj || new UserQueryDto();
      if (data.hasOwnProperty('nameOrEmail'))
        obj.nameOrEmail = ApiClient.convertToType(data['nameOrEmail'], 'String');
    }
    return obj;
  }
}

/**
 * @member {String} nameOrEmail
 */
UserQueryDto.prototype.nameOrEmail = undefined;

